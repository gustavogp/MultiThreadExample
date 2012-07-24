public class MultiThreadExample {

        /**
         * @param args
         * @throws InterruptedException
         */
        static int soma = 0;

        private static class Calculator implements Runnable {
                private String dadoLocal = null;

                //constructor
                private Calculator(String dado) {
                        dadoLocal = dado;
                }

                public synchronized void run() {
                        soma += Math.pow(dadoLocal.length(),5);
                        try {
                                Thread.sleep(1000);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                }

        }

        public static void main(String[] args) throws InterruptedException {
                String [] data = {"dado1", "dado22", "dado333", "dado4444", "dado55555", "dado666666",
                                                "dado7777777", "dado88888888", "dado999999999", "dado0000000010"};
                long startTime = System.currentTimeMillis();
                long finishTime = 0;
                boolean isAnyAlive = true;

                for(String data2: data) {
                        soma += Math.pow(data2.length(),5);
                        Thread.sleep(1000);
                }

                finishTime = System.currentTimeMillis();
                System.out.println("soma total = " + soma + " calculado em unico thread em " + (finishTime-startTime)/1000 + "s");
                //zerar o field soma, startTime
                soma = 0;
                startTime = System.currentTimeMillis();

                for(String data2: data) {
                        Thread t = new Thread (new Calculator(data2));
                        t.start();
                }
        //      Make sure all threads have executed before we proceed
                while (isAnyAlive) {
                        if (Thread.activeCount() >1) {
                                        Thread.sleep(1000);
                        } else if (Thread.activeCount() == 1) {
                                isAnyAlive = false;
                        }
                }
                finishTime = System.currentTimeMillis();
                System.out.println("soma total = " + soma + " calculado em " +
                data.length + " threads em " + (finishTime-startTime)/1000 + "s");
        }

}
