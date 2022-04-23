//
// This file is part of an OMNeT++/OMNEST simulation example.
//
// Copyright (C) 1992-2008 Andras Varga
//
// This file is distributed WITHOUT ANY WARRANTY. See the file
// `license' for details on this and other legal matters.
//

//
// Simple queueing network: generator + FIFO + sink.
//
package elec3500.simulations.FIFO;
import elec3500.FIFO.SOURCE;
import elec3500.FIFO.FIFO;
import elec3500.FIFO.SINK;

network fifoNet
{
    submodules:
        gen: SOURCE {
            parameters:
                @display("p=89,100");
        }
        fifo: FIFO {
            parameters:
                @display("p=209,100");
        }
        sink: SINK {
            parameters:
                @display("p=329,100");
        }
    connections:
        gen.out --> fifo.in;
        fifo.out --> sink.in;
}


