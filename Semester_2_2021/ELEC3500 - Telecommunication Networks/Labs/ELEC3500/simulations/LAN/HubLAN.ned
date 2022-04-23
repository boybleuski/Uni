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

package elec3500.simulations.LAN;

import inet.common.misc.ThruputMeteringChannel;
import inet.linklayer.ethernet.EtherBus;
import inet.linklayer.ethernet.EtherHub;
import inet.node.ethernet.EtherHost;
import inet.node.ethernet.EtherSwitch;
import ned.DatarateChannel;

network HubLAN
{
    parameters:
        int  numHosts;
    types:
        channel C extends DatarateChannel
        {
                delay = 0;
                datarate = 100Mbps;
        }
        
    submodules:
        host[numHosts]: EtherHost {
            parameters:
                @display(p=200,200,ri,50,50);
        }
        
        hub: EtherHub {
            parameters:
                @display("p=200,200");
            gates:
                ethg[];
        }
        
    connections:
        for i=0..numHosts-1 {
                
                hub.ethg++ <--> C <--> host[i].ethg;
        }
}
