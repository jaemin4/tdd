package pro.tdd.proejct.unit;

import org.junit.jupiter.api.BeforeEach;
import pro.tdd.proejct.database.UserPointTable;
import pro.tdd.proejct.service.PointService;

import static org.mockito.Mockito.mock;

public class PointCheckTest {

    private UserPointTable userPointTable;
    private PointService pointService;

    @BeforeEach
    void setUp() {
        userPointTable = mock(UserPointTable.class);
        pointService = new PointService(userPointTable);
    }

}
