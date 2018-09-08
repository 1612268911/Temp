///**
// * Copyright (C), 2015-2018, XXX有限公司
// * FileName: JsonTest
// * Author:   jj
// * Date:     2018/7/27 20:57
// * Description: JSONObject和JSONArray
// * History:
// * <author>          <time>          <version>          <desc>
// * 作者姓名           修改时间           版本号              描述
// */
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 〈一句话功能简述〉<br>
// * 〈JSONObject和JSONArray〉
// *
// * @author jj
// * @create 2018/7/27
// * @since 1.0.0
// */
//public class JsonTest {
//    public static void main(String[] args){
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.fluentPut("name","xjj");
//        jsonObject.fluentPut("age",18);
//        jsonObject.fluentPut("age",30);
//        System.out.println("jsonObject="+jsonObject);
//
//        JSONArray jsonArray = new JSONArray();
//        jsonArray.add(jsonObject);
//        jsonObject = new JSONObject();
//        jsonObject.fluentPut("name","lll");
//        jsonObject.fluentPut("age",15);
//        jsonArray.add(jsonObject);
//        for(int i=0;i<jsonArray.size();i++){
//            JSONObject object = jsonArray.getJSONObject(i);//JSONArray->JSONObject
//            System.out.println("object = "+object);
//        }
//        String str = jsonArray.toJSONString();
//        System.out.println("str = "+str);//str = [{"name":"xjj","age":30},{"name":"lll","age":15}]
//
//        System.out.println("******************************");
//        String jsonString = "[{'name':'xjj','age':30},{'name':'lll','age':15}]";//size必须大于1
//        JSONArray array = JSONArray.parseArray(jsonString);//String ->JSONArray
//        System.out.println("array = "+array);
//
//        String jsonString2 = "{'name':'xjj','age':30}";//只能一个
//        JSONObject array1 = JSONObject.parseObject(jsonString2);//String ->JSONObject
//        System.out.println("array1 = "+array1);
//
//        String newJson = "["+jsonObject.toString()+"]";
//        //json与jsonarray的区别在于有没有'[]'，用newJson.indexOf("[")>0判断
//        //也可将json组装成jsonarray只要在json字符串加上"[]",如 String newJson = "["+jsonObject.toString()+"]";
//        ObjectMapper objectMapper = new ObjectMapper();
//        List<String> startList = JSONArray.parseArray(newJson,String.class);
//
//        List<Map<String,Object>> list = new ArrayList<>();
//
//        for(String s:startList){
//            Map<String,Object> map = new HashMap<>();
//            JSONObject object = JSONObject.parseObject(s);
//            for(Map.Entry<String,Object> entry:object.entrySet()){
//                map.put(entry.getKey(),entry.getValue());
//            }
//            list.add(map);
//        }
//        if(CollectionUtils.isNotEmpty(list)){
//            for(Map<String,Object> map :list){
//                System.out.println(map);
//            }
//        }
//    }
//}