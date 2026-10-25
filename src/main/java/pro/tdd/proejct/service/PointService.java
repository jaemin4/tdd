package pro.tdd.proejct.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.tdd.proejct.database.PointHistoryTable;
import pro.tdd.proejct.database.UserPointTable;
import pro.tdd.proejct.model.entity.PointHistory;
import pro.tdd.proejct.model.entity.UserPoint;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PointService {

    private final UserPointTable userPointTable;
    private final PointHistoryTable pointHistoryTable;

    //특정 유저의 포인트를 조회한다.
    public long checkPoint (long userId){
        UserPoint userPoint = userPointTable.selectById(userId);

        if(userPoint.point() < 0){
            throw new RuntimeException("계좌의 잔액이 음수입니다.");
        }

        return userPoint.point();
    }

    //특정 유저의 포인트 충전/이용 내역을 조회
    public List<PointHistory> checkHistories(long userId){
        return pointHistoryTable.selectAllByUserId(userId);
    }

}
