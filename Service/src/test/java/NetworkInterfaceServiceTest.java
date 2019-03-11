import com.service.exception.InterfaceNotFoundException;
import com.service.model.NetworkInterface;
import com.service.parser.NetworkInterfaceParser;
import com.service.service.NetworkInterfaceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NetworkInterfaceServiceTest {

    @Mock
    private NetworkInterfaceParser parser;

    @InjectMocks
    NetworkInterfaceService service;

    @Test
    public void getAllInterfaceNames() throws IOException, InterruptedException {
        when(parser.parseNames()).thenReturn(new ArrayList<>());
        service.getAllInterfaceNames();
        verify(parser).parseNames();
    }

    @Test(expected = IOException.class)
    public void getAllInterfaceNamesWithIOException() throws IOException, InterruptedException {
        when(parser.parseNames()).thenThrow(IOException.class);
        service.getAllInterfaceNames();
        verify(parser).parseNames();
    }

    @Test(expected = InterruptedException.class)
    public void getAllInterfaceNamesWithInterruptedException() throws IOException, InterruptedException {
        when(parser.parseNames()).thenThrow(InterruptedException.class);
        service.getAllInterfaceNames();
        verify(parser).parseNames();
    }

    @Test
    public void getInterfaceByName() throws IOException, InterruptedException, InterfaceNotFoundException {
        when(parser.parseNames()).thenReturn(new ArrayList<>(Arrays.asList("name", "name2")));
        when(parser.parseByName("name")).thenReturn(new NetworkInterface());
        service.getInterfaceByName("name");
        verify(parser).parseByName("name");
    }

    @Test(expected = InterfaceNotFoundException.class)
    public void getInterfaceByNameWithWrongName() throws IOException, InterruptedException, InterfaceNotFoundException {
        when(parser.parseNames()).thenReturn(new ArrayList<>(Arrays.asList("name1", "name2")));
        service.getInterfaceByName("name");
        verify(parser).parseByName("name");
    }

    @Test(expected = IOException.class)
    public void getInterfaceByNameWithIOException() throws IOException, InterruptedException,
                                                                        InterfaceNotFoundException {

        when(parser.parseNames()).thenThrow(IOException.class);
        service.getInterfaceByName("name");
        verify(parser).parseByName("name");
    }

    @Test(expected = InterruptedException.class)
    public void getInterfaceByNameWithInterruptedException() throws IOException, InterruptedException,
            InterfaceNotFoundException {

        when(parser.parseNames()).thenThrow(InterruptedException.class);
        service.getInterfaceByName("name");
        verify(parser).parseByName("name");
    }
}
