//
// This file is part of an OMNeT++/OMNEST simulation example.
//
// Copyright (C) 1992-2008 Andras Varga
//
// This file is distributed WITHOUT ANY WARRANTY. See the file
// `license' for details on this and other legal matters.
//


#include "ABSTRACTFIFO.h"
namespace ELEC3500 {

ABSTRACTFIFO::ABSTRACTFIFO()
{
    msgServiced = endServiceMsg = NULL;
}

ABSTRACTFIFO::~ABSTRACTFIFO()
{
    delete msgServiced;
    cancelAndDelete(endServiceMsg);
}

void ABSTRACTFIFO::initialize()
{
    endServiceMsg = new cMessage("end-service");
    queue.setName("queue");

    qlenSignal = registerSignal("qlen");
    busySignal = registerSignal("busy");
    queueingTimeSignal = registerSignal("queueingTime");
    emit(qlenSignal, queue.getLength());
    emit(busySignal, false);
}

void ABSTRACTFIFO::handleMessage(cMessage *msg)
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
        queue.insert( msg );
        msg->setTimestamp();
        emit(qlenSignal, queue.getLength());
    }
}

};
