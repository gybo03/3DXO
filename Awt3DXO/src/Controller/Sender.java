package Controller;

import java.util.ArrayList;
import java.util.List;

public abstract class Sender {
    private List<Recipient> recipients=new ArrayList<Recipient>();

    public void addRecipient(Recipient recipient){
        recipients.add(recipient);
    }

    public void removeRecipient(Recipient recipient){
        recipients.remove(recipient);
    }

    public void notifyRecipient(Object that){
        for (Recipient recipient:recipients) {
            recipient.update(that);
        }
    }
}
