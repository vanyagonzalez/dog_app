package com.piv;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*руддщ*/
public class ThreadLocalDateFormatExample {

    private static final DateFormat format =
            new SimpleDateFormat("yyyyMMdd");

    private static final ThreadLocal<DateFormat> df
            = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

    public static Date convert(String source) throws ParseException {
//        return format.parse(source);
        return df.get().parse(source);
    }

    public static void main(String[] args) throws Exception {

        Callable<Date> task = new Callable<Date>(){
            public Date call() throws Exception {
                return convert("20101022");
            }
        };

        //pool with 5 threads
        ExecutorService exec = Executors.newFixedThreadPool(5);
        List<Future<Date>> results = new ArrayList<Future<Date>>();

        //perform 10 date conversions
        for(int i = 0 ; i < 10 ; i++){
            results.add(exec.submit(task));
        }
        exec.shutdown();

        //look at the results
        for(Future<Date> result : results){
            System.out.println(result.get());
        }
    }
}
