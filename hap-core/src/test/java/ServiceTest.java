import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.hand.hap.account.dto.Role;
import com.hand.hap.account.dto.RoleExt;
import com.hand.hap.account.mapper.RoleMapper;
import com.hand.hap.account.mapper.UserRoleMapper;
import com.hand.hap.account.service.IRole;
import com.hand.hap.account.service.impl.RoleServiceImpl;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.impl.RequestHelper;
import com.hand.hap.core.impl.ServiceRequest;
import com.hand.hap.function.service.IRoleFunctionService;

/**
 * Created by hailor on 16/9/21.
 */
@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {
    @Mock
    private RoleMapper roleMapper;

    @Mock
    private UserRoleMapper userRoleMapper;

    @Mock
    private IRoleFunctionService roleFunctionService;

    @InjectMocks
    private RoleServiceImpl roleService;

    IRequest request;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        //roleService = new RoleServiceImpl();
        //roleService.setRoleMapper(roleMapper);
        //roleService.setRoleFunctionService(roleFunctionService);
        //roleService.setUserRoleMapper(userRoleMapper);
        request= new ServiceRequest();
        request.setLocale("zh_CN");
        RequestHelper.setCurrentRequest(request);
    }

    @Test
    public void test(){
        List<Role> someList = new ArrayList<Role>();
        Role firstRole = new Role();
        firstRole.setRoleId(1L);
        someList.add(firstRole);
        RoleExt roleExt = new RoleExt();
        roleExt.setUserId(anyLong());
        when(roleMapper.selectRoleNotUserRoles(roleExt)).thenReturn(someList);
        List<IRole> roles = roleService.selectRoleNotUserRoles(request,roleExt,1,0);
        assertEquals((long)roles.get(0).getRoleId(),1L);
        verify(roleMapper, times(1)).selectRoleNotUserRoles(roleExt);
    }
}
