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

#include "Server.h"

namespace ELEC3500 {
Define_Module(Server);

Server::Server()
{
    endRxEvent = nullptr;
}

Server::~Server()
{
    cancelAndDelete(endRxEvent);
}

void Server::initialize()
{
    channelStateSignal = registerSignal("channelState");
    endRxEvent = new cMessage("end-reception");
    channelBusy = false;
    emit(channelStateSignal,IDLE);

    gate("in")->setDeliverOnReceptionStart(true);

    currentCollisionNumFrames = 0;
    receiveCounter = 0;
    WATCH(currentCollisionNumFrames);

    receiveBeginSignal = registerSignal("receiveBegin");
    receiveSignal = registerSignal("receive");
    collisionSignal = registerSignal("collision");
    collisionLengthSignal = registerSignal("collisionLength");

    emit(receiveSignal, 0L);
    emit(receiveBeginSignal, 0L);
}


void Server::handleMessage(cMessage *msg)
{
    if (msg==endRxEvent)
    {
        EV << "reception finished\n";
        channelBusy = false;
        emit(channelStateSignal,IDLE);

        // update statistics
        simtime_t dt = simTime() - recvStartTime;
        if (currentCollisionNumFrames==0)
        {
            // start of reception at recvStartTime
            cTimestampedValue tmp(recvStartTime, 1l);
            emit(receiveSignal, &tmp);
            // end of reception now
            emit(receiveSignal, 0);
        }
        else
        {
            // start of collision at recvStartTime
            cTimestampedValue tmp(recvStartTime, currentCollisionNumFrames);
            emit(collisionSignal, &tmp);

            emit(collisionLengthSignal, dt);
        }

        currentCollisionNumFrames = 0;
        receiveCounter = 0;
        emit(receiveBeginSignal, receiveCounter);

    }
    else
    {
        cPacket *pkt = check_and_cast<cPacket *>(msg);

        ASSERT(pkt->isReceptionStart());
        simtime_t endReceptionTime = simTime() + pkt->getDuration();

        emit(receiveBeginSignal, ++receiveCounter);

        if (!channelBusy)
        {
            EV << "started receiving\n";
            recvStartTime = simTime();
            channelBusy = true;
            emit(channelStateSignal, TRANSMISSION);
            scheduleAt(endReceptionTime, endRxEvent);
        }
        else
        {
            EV << "another frame arrived while receiving -- collision!\n";
            emit(channelStateSignal, COLLISION);

            if (currentCollisionNumFrames==0)
                currentCollisionNumFrames = 2;
            else
                currentCollisionNumFrames++;

            if (endReceptionTime > endRxEvent->getArrivalTime())
            {
                cancelEvent(endRxEvent);
                scheduleAt(endReceptionTime, endRxEvent);
            }

            // update network graphics
            if (hasGUI())
            {
                char buf[32];
                sprintf(buf, "Collision! (%ld frames)", currentCollisionNumFrames);
                bubble(buf);
            }
        }
        channelBusy = true;
        delete pkt;
    }
}


void Server::refreshDisplay() const
{
    if (!channelBusy) {
        getDisplayString().setTagArg("i2", 0, "status/off");
        getDisplayString().setTagArg("t", 0, "");
    }
    else if (currentCollisionNumFrames == 0) {
        getDisplayString().setTagArg("i2", 0, "status/yellow");
        getDisplayString().setTagArg("t", 0, "RECEIVE");
        getDisplayString().setTagArg("t", 2, "#808000");
    }
    else {
        getDisplayString().setTagArg("i2", 0, "status/red");
        getDisplayString().setTagArg("t", 0, "COLLISION");
        getDisplayString().setTagArg("t", 2, "#800000");
    }
}
void Server::finish()
{
    EV << "duration: " << simTime() << endl;

    recordScalar("duration", simTime());
}

}; //namespace
