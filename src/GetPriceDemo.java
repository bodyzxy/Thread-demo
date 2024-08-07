package src;

import model.PlatForm;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * TODO 案例，计算各个平台某商品价位，以异步线程来计算
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/8/6 12:50
 */
public class GetPriceDemo {

    static List<PlatForm> list = Arrays.asList(new PlatForm("js"), new PlatForm("taobao"), new PlatForm("tianmao"));

    /**
     * stream()是将其转换成流,map()是对流中的每个数据进行处理
     * CompletableFuture.supplyAsync() 用来启动一个异步任务。里面使用Lambda进行计算
     * toList()将其转换成列表，其后面.stream()是将list转换成一个新流
     * 最后这个map() 对流中的每个 CompletableFuture 对象调用 join() 方法，这会等待异步任务完成，并获取结果
     * collect(Collectors.toList()) 将所有的字符串结果收集到一个列表中并返回。
     * @param lstPlatForms
     * @param name
     * @return
     */
    public static List<String> getCompletableFuture(List<PlatForm> lstPlatForms, String name) {
        return lstPlatForms.stream().map(platForm ->
            CompletableFuture.supplyAsync(() ->
                String.format("《" + name + "》" + "in %s price is %.2f",
                        platForm.getName(),
                        platForm.calcPrice(name))))
                .toList()
                .stream()
                .map(s -> s.join()).collect(Collectors.toList());
    }

    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        List<String> list1 = getCompletableFuture(list,"masql");
        for(String s : list1){
            System.out.println(s);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("------costTime: " + (endTime - startTime) + " 毫秒");
    }
}
