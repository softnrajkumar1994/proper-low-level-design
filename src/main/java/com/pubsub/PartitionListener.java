package com.pubsub;

public class PartitionListener implements Runnable {

    private final Partition partition;
    public volatile ListenerState state;

    public PartitionListener(Partition partition) {
        this.partition = partition;
        this.state = ListenerState.OPEN;
    }

    @Override
    public void run() {
        while (state == ListenerState.OPEN) {
            try {
                String message = partition.getMessages().take(); // Blocks until message available
                if (message != null && partition.getConsumer() != null) {
                    System.out.println(
                            "Consumed Message: " + message +
                                    " | Topic: " + partition.getTopic().getName() +
                                    " | Partition ID: " + partition.getPartitionId() +
                                    " | Consumer ID: " + partition.getConsumer().getConsumerId()
                    );
                }
            } catch (InterruptedException e) {
                System.out.println("PartitionListener interrupted for partition: " + partition.getPartitionId());
                Thread.currentThread().interrupt(); // restore interrupt flag
                break;
            } catch (Exception e) {
                System.out.println("Error in PartitionListener: " + e.getMessage());
                e.printStackTrace();
            }
        }

        System.out.println("Partition " + partition.getPartitionId() + " closed for topic: " + partition.getTopic().getName());
    }

     public static enum ListenerState {
        OPEN,
        CLOSED
    }
}
