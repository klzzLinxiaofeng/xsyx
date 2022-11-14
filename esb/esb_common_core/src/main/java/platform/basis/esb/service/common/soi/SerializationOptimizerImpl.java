/**
 *
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package platform.basis.esb.service.common.soi;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.basis.esb.service.common.contants.EsbConfig;
import platform.basis.esb.service.common.util.ClassUtil;

import java.util.Collection;
import java.util.List;

//import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;

/**
 * This class must be accessible from both the provider and consumer
 *
 */
public class SerializationOptimizerImpl  {


	@SuppressWarnings("rawtypes")
	public Collection<Class> getSerializableClasses() {
		List<Class> clazzs = ClassUtil.getClasses(EsbConfig.getInstance().getSerializationPackagePath().trim().split(","));
		clazzs.add(Page.class);
		clazzs.add(Order.class);
		return clazzs;
	}

}
