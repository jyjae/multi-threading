package org.interrupt.exception;

import java.math.BigInteger;

public class Main2 {

    public static void main(String[] args) {
        Thread thread = new Thread(new LongComputationTask(new BigInteger("500000000000000"), new BigInteger("500000000000000")));

        thread.start();

        // thread.interrupt(); <- 여기 로직에서는 interrupt를 호출해도 역부족이다 이를 처리할 메서드나 로직이 없기때문이다
        // 그렇다면 시간을 오래잡아먹는 로직에서 인터럽트 당했는지 체크하는 로직을 추가해주어야한다.
        thread.interrupt();
    }

    private static class LongComputationTask implements Runnable {
        private BigInteger base;
        private BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + "^" +power +" = "+pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for(BigInteger i = BigInteger.ZERO; i.compareTo(power)!=0; i = i.add(BigInteger.ONE)) {
                if(Thread.currentThread().isInterrupted()) {
                    System.out.println("Prematurely interrupted computation");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }
            return result;
        }
    }
}
