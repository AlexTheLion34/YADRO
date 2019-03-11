import com.service.controller.Controller;
import com.service.exception.InterfaceNotFoundException;
import com.service.exception.VersionDoesNotMatchException;
import com.service.model.NetworkInterface;
import com.service.service.NetworkInterfaceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.HashMap;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    @Mock
    private NetworkInterfaceService service;

    @InjectMocks
    Controller controller;

    @Test
    public void testGetVersion() {
        controller.getVersion();
        verify(service).getCurrentApiVersion();
    }

    @Test
    public void testGetAllInterfaces() throws IOException, InterruptedException, VersionDoesNotMatchException {
        when(service.getCurrentApiVersion()).thenReturn(new HashMap<String, String>(){{put("version", "v1");}});
        controller.getAllInterfaces("v1");
        verify(service).getAllInterfaceNames();
    }

    @Test(expected = VersionDoesNotMatchException.class)
    public void testGetAllInterfacesWithWrongVersion() throws InterruptedException, VersionDoesNotMatchException,
                                                                                                        IOException {

        when(service.getCurrentApiVersion()).thenReturn(new HashMap<String, String>(){{put("version", "v1");}});
        controller.getAllInterfaces("v2");
        verify(service).getAllInterfaceNames();
    }

    @Test
    public void testGetInterface() throws InterruptedException, IOException, InterfaceNotFoundException,
                                                                                        VersionDoesNotMatchException {

        when(service.getCurrentApiVersion()).thenReturn(new HashMap<String, String>(){{put("version", "v1");}});
        when(service.getInterfaceByName("lo")).thenReturn(new NetworkInterface());
        controller.getInterface("v1", "lo");
        verify(service).getInterfaceByName("lo");
    }

    @Test(expected = VersionDoesNotMatchException.class)
    public void testGetInterfaceWithWrongVersion() throws InterruptedException, IOException, InterfaceNotFoundException,
                                                                                        VersionDoesNotMatchException {

        when(service.getCurrentApiVersion()).thenReturn(new HashMap<String, String>(){{put("version", "v1");}});
        controller.getInterface("v5", "lo");
        verify(service).getInterfaceByName("lo");
    }

    @Test(expected = InterfaceNotFoundException.class)
    public void testGetInterfaceWithWrongName() throws InterruptedException, InterfaceNotFoundException, IOException,
                                                                                        VersionDoesNotMatchException {

        when(service.getCurrentApiVersion()).thenReturn(new HashMap<String, String>(){{put("version", "v1");}});
        when(service.getInterfaceByName("lodewd")).thenThrow(InterfaceNotFoundException.class);
        controller.getInterface("v1", "lodewd");
        verify(service).getInterfaceByName("lodewd");
    }
}
