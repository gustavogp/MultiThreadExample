public class MultiThreadExample {

        /**
         * @param args
         * @throws InterruptedException
         */
        static int soma1, soma2;
        static long tempo1, tempo2;
        static final String [] data = {"dado1", "dado22", "dado333", "dado4444", "dado55555", "dado666666",
            "dado7777777", "dado88888888", "dado999999999", "dado0000000010"};;

        private static class Calculator implements Runnable {
                private String dadoLocal = null;

                //constructor
                private Calculator(String dado) {
                        dadoLocal = dado;
                }

                public synchronized void run() {
                        soma2 += Math.pow(dadoLocal.length(),5);
                        try {
                                Thread.sleep(1000);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                }

        }

        public static void main(String[] args) throws InterruptedException {
        		soma1=0;
        		soma2=0;
                long startTime = System.currentTimeMillis();
                long finishTime = 0;
           //     boolean isAnyAlive = true;

                for(String data2: data) {
                        soma1 += Math.pow(data2.length(),5);
                        Thread.sleep(1000);
                }

                finishTime = System.currentTimeMillis();
                tempo1 = finishTime-startTime;
                System.out.println("soma total = " + soma1 + " calculado em unico thread em " + tempo1/1000 + "s");
                
                //zerar o field startTime
                startTime = System.currentTimeMillis();

                //encapsulating all threads in a single worker thread
                Thread worker = new Thread(new Runnable(){
                	public void run(){
                		for(String data2: data) {
                            Thread t = new Thread (new Calculator(data2));
                            t.start();
                    }
                	};
                });
                worker.start();
                
        //      Make sure all threads have executed before we proceed
        /*        while (isAnyAlive) {
                        if (Thread.activeCount() >5) {
                                        Thread.sleep(1000);
                        } else {
                                isAnyAlive = false;
                        }
                }
        */
                while(worker.isAlive()){
                	Thread.sleep(100);
                };
                finishTime = System.currentTimeMillis();
                tempo2 = finishTime-startTime;
                System.out.println("soma total = " + soma2 + " calculado em " +
                data.length + " threads em " + tempo2/1000 + "s");
        }

}
