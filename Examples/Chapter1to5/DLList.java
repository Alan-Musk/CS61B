package Chapter1to5;

public class DLList<BleepBlorp>{
    private IntNode sentinel;
    private int size;

    public class IntNode
    {
        public IntNode prev;
        public BleepBlorp item;
        public IntNode next;
    }

}
