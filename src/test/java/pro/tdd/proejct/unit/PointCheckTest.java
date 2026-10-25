package pro.tdd.proejct.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.tdd.proejct.database.UserPointTable;
import pro.tdd.proejct.model.entity.UserPoint;
import pro.tdd.proejct.service.PointService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PointCheckTest {

    @Mock
    private  UserPointTable userPointTable;

    @InjectMocks
    private PointService pointService;

    @Test
    @DisplayName("특정 유저의 포인트를 조회")
    void check_point(){
        //given
        long userId = 1L;
        Long point = 10000L;
        given(userPointTable.selectById(userId))
                .willReturn(new UserPoint(userId,point,0L));

        //when
        Long result = pointService.checkPoint(userId);

        //then
        assertThat(result).isEqualTo(point);
        verify(userPointTable).selectById(userId);

    }

    @Test
    @DisplayName("특정 유저의 포인트가 0이면 0을 반환한다.")
    void check_point_zero(){
        long userId = 2L;
        Long point = 0L;
        given(userPointTable.selectById(userId))
                .willReturn(new UserPoint(userId,point,0L));

        Long result = pointService.checkPoint(userId);

        assertThat(result).isZero();
        verify(userPointTable).selectById(userId);


    }

    @Test
    @DisplayName("특정 유저의 포인트가 음수면 오류가 발생한다.")
    void check_point_minus(){
        long userId = 3L;
        Long point = -1L;

        given(userPointTable.selectById(userId))
                .willReturn(new UserPoint(userId,point,0L));

        assertThatThrownBy(() -> pointService.checkPoint(userId))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("계좌의 잔액이 음수입니다.");

    }

}
