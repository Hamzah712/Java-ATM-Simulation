
/*Recall from the requirements document that the ATM allows the user up to two minutes to insert an envelope. The current version of method isEnvelopeReceived simply
returns true immediately (line 10), because this is only a software simulation, and we
assume that the user has inserted an envelope within the required time frame. If an actual
hardware deposit slot were connected to our system, method isEnvelopeReceived might
be implemented to wait for a maximum of two minutes to receive a signal from the hardware deposit slot indicating that the user has indeed inserted a deposit envelope. If
isEnvelopeReceived were to receive such a signal within two minutes, the method would
return true. If two minutes elapsed and the method still had not received a signal, then
the method would return false.*/

public class DepositSlot {
	
	// indicates whether envelope was received (always returns true,
	// because this is only a software simulation of a real deposit slot)
	public boolean isEnvelopeReceived()
	{
		return true;
	}
}
