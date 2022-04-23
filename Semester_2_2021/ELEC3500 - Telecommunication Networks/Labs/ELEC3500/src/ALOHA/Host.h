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

#ifndef __ELEC3500_HOST_H_
#define __ELEC3500_HOST_H_

#include <omnetpp.h>

using namespace omnetpp;

namespace ELEC3500 {

/**

 * Aloha host; see NED file for more info.
 */
class Host : public cSimpleModule
{
private:
    // parameters
    simtime_t radioDelay;
    double txRate;
    cPar *iaTime;
    cPar *pkLenBits;
    simtime_t slotTime;
    bool isSlotted;

    // state variables, event pointers etc
    cModule *server;
    cMessage *endTxEvent;
    enum {IDLE=0, TRANSMIT=1} state;
    simsignal_t stateSignal;
    int pkCounter;

public:
    Host();
    virtual ~Host();

protected:
    virtual void initialize();
    virtual void handleMessage(cMessage *msg);
    virtual void refreshDisplay() const override;
    simtime_t getNextTransmissionTime();
};

}; //namespace
#endif
