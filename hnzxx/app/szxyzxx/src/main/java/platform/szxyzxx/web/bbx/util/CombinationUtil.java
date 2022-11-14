package platform.szxyzxx.web.bbx.util;//package platform.szxyzxx.web.bbx.util;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import platform.education.clazz.model.Course;
//
//public class CombinationUtil {
//
//
//	public static void main(String[] args) {
//        /*List<Character> data = new ArrayList<Character>();
//        data.add('1');
//        data.add('2');
//        data.add('3');
//        data.add('4');
//        data.add('5');
//        data.add('6');
//
//        List<List<Object>> reuslt = new ArrayList<List<Object>>();
//        TEstaaaaa.combinerSelect(data, new ArrayList<Character>(), data.size(), 3, reuslt);
//*/
//		 List<Course> data = new ArrayList<Course>();
//		 Course c1 = new Course();
//		 c1.setId(1);
//		 Course c2 = new Course();
//		 c2.setId(2);
//		 Course c3 = new Course();
//		 c3.setId(3);
//		 Course c4 = new Course();
//		 c4.setId(4);
//		 Course c5 = new Course();
//		 c5.setId(5);
//		 Course c6 = new Course();
//		 c6.setId(6);
//	        data.add(c1);
//	        data.add(c2);
//	        data.add(c3);
//	        data.add(c4);
//	        data.add(c5);
//	        data.add(c6);
//
//	        List<List<Object>> reuslt = new ArrayList<List<Object>>();
//	        CombinationUtil.combinerSelect(data, new ArrayList<Course>(), data.size(), 3, reuslt);
//
//
//        for(List<Object> r: reuslt){
//        	for(Object rr: r){
//        		System.out.print(((Course)rr).getId());
//        	}
//        	System.out.println("");
//        }
//    }
//
//
//	public static <E> void combinerSelect(List<E> data, List<E> workSpace, int n, int k, List<List<Object>> reuslt) {
//        List<E> copyData;
//        List<E> copyWorkSpace;
//
//        if(workSpace.size() == k) {
//        	List<Object> list = new ArrayList<Object>();
//            for(Object c : workSpace){
//            	list.add(c);
//            }
//            reuslt.add(list);
//        }
//
//        for(int i = 0; i < data.size(); i++) {
//            copyData = new ArrayList<E>(data);
//            copyWorkSpace = new ArrayList<E>(workSpace);
//
//            copyWorkSpace.add(copyData.get(i));
//            for(int j = i; j >=  0; j--)
//                copyData.remove(j);
//            combinerSelect(copyData, copyWorkSpace, n, k, reuslt);
//        }
//
//    }
//}
