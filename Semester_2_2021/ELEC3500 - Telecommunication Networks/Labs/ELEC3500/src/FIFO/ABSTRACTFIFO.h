//
// This file is part of an OMNeT++/OMNEST simulation example.
//
// Copyright (C) 1992-2008 Andras Varga
//
// This file is distributed WITHOUT ANY WARRANTY. See the file
// `license' for details on this and other legal matters.
//


#ifndef __ELEC3500_ABSTRACTFIFO_H
#define __ELEC3500_ABSTRACTFIFO_H

#include <omnetpp.h>
using namespace omnetpp;

namespace ELEC3500 {
/**
 * Abstract base class for single-server queues. Subclasses should
 * define startService() and endService(), and may override other
 * virtual functions.
 */
class ABSTRACTFIFO : public cSimpleModule
{
  protected:
    cMessage *msgServiced;
    cMessage *endServiceMsg;
    cQueue queue;
    simsignal_t qlenSignal;
    simsignal_t busySignal;
    simsignal_t queueingTimeSignal;

  public:
    ABSTRACTFIFO();
    virtual ~ABSTRACTFIFO();

  protected:
    virtual void initialize();
    virtual void handleMessage(cMessage *msg);

    // hook functions to (re)define behaviour
    virtual void arrival(cMessage *msg) {}
    virtual simtime_t startService(cMessage *msg) = 0;
    virtual void endService(cMessage *msg) = 0;
};

};

#endif
