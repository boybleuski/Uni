//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
// 
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public License
// along with this program.  If not, see http://www.gnu.org/licenses/.
// 

#include "FIFO.h"
namespace ELEC3500 {
Define_Module(FIFO);


simtime_t FIFO::startService(cMessage *msg)
{
    EV << "Starting service of " << msg->getName() << endl;
    return ((double)((cPacket *)msg)->getBitLength())/par("serviceRate").doubleValue();
    //return par("serviceTime");
}

void FIFO::endService(cMessage *msg)
{
    EV << "Completed service of " << msg->getName() << endl;
    send( msg, "out" );
}

void FIFO::handleMessage(cMessage *msg)
{
    if (msg==endServiceMsg)
    {
        endService( msgServiced );
        if (queue.isEmpty())
        {
            msgServiced = NULL;
            emit(busySignal, false);
        }
        else
        {
            msgServiced = (cMessage *) queue.pop();
            emit(qlenSignal, queue.getLength());
            emit(queueingTimeSignal, simTime() - msgServiced->getTimestamp());
            simtime_t serviceTime = startService( msgServiced );
            scheduleAt( simTime()+serviceTime, endServiceMsg );
        }
    }
    else if (!msgServiced)
    {
        arrival( msg );
        msgServiced = msg;
        emit(queueingTimeSignal, SIMTIME_ZERO);
        simtime_t serviceTime = startService( msgServiced );
        scheduleAt( simTime()+serviceTime, endServiceMsg );
        emit(busySignal, true);
    }
    else
    {
        arrival( msg );

        if((int)par("queueLength")<0||queue.getLength()<(int)par("queueLength"))
        {
            queue.insert(msg);
            msg->setTimestamp();
            emit(qlenSignal, queue.getLength());
        }
        else
        {
            delete msg;
            Discarded++;
            emit(numDiscardedSignal, Discarded);
        }


    }
}
void FIFO::initialize()
{
    ABSTRACTFIFO::initialize();
    Discarded = 0;
    numDiscardedSignal = registerSignal("numDiscarded");


}
};
