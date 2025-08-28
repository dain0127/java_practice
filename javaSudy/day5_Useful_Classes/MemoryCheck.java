import java.lang.OutOfMemoryError;

class MemoryCheck {
    public static void main(String[] args) {
        long heapMax = Runtime.getRuntime().maxMemory();
        long heapInit = Runtime.getRuntime().totalMemory();
        System.out.println("초기 Heap 크기: " + heapInit / (1024*1024) + " MB");
        System.out.println("최대 Heap 크기: " + heapMax / (1024*1024) + " MB");

        
        if (new OutOfMemoryError() instanceof Error){
            System.out.println("OutOfMemoryError is one of Error");
        } else{
            System.out.println("OutOfMemoryError is not one of Error");
        }


        int[][][] arr = new int[1024][1024][1024]; //4*1024^3 byte = 4GB = 4096MB
        //OutOfMemory


    }
}
