package platform.education.generalTeachingAffair.service.test.base;


import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
public class BaseTest {
	public static void main(String[] args) {
		List<Float> list = new ArrayList<Float>();
		list.add(71.01f);
		list.add(72.01f);
		list.add(75.01f);
        // 接近的数字
        float nearNum = 73.09f;
        // 差值实始化
        float diffNum = Math.abs(list.get(0) - nearNum);
        // 最终结果
        float result = list.get(0);
        for (Float integer : list)
        {
        	float diffNumTemp = Math.abs(integer - nearNum);
            if (diffNumTemp < diffNum)
            {
                diffNum = diffNumTemp;
                result = integer;
            }
        }
        System.out.println(result);
	}
}
