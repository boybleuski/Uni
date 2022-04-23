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

#include "SOURCE.h"
namespace ELEC3500 {
Define_Module(SOURCE);

SOURCE::SOURCE()
{
    sendMessageEvent = NULL;
}

SOURCE::~SOURCE()
{
    cancelAndDelete(sendMessageEvent);
}

void SOURCE::initialize()
{
    sendMessageEvent = new cMessage("sendMessageEvent");
    scheduleAt(simTime(), sendMessageEvent);

}

void SOURCE::handleMessage(cMessage *msg)
{
    ASSERT(msg==sendMessageEvent);

    //cMessage *job = new cMessage("job");
    cPacket *job = new cPacket("job");
    job->setBitLength(int(par("packetLength").doubleValue()));
    //job->setTimestamp(simTime());

    send(job, "out");

    scheduleAt(simTime()+par("sendIaTime").doubleValue(), sendMessageEvent);

}
};
