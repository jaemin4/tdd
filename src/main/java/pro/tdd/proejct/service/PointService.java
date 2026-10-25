package pro.tdd.proejct.service;

import lombok.RequiredArgsConstructor;
import pro.tdd.proejct.database.UserPointTable;
import pro.tdd.proejct.model.entity.UserPoint;

@RequiredArgsConstructor
public class PointService {

    private final UserPointTable userPointTable;

    //특정 유저의 포인트를 조회한다.
    public long checkPoint (long userId){
        UserPoint userPoint = userPointTable.selectById(userId);

        if(userPoint.point() < 0){
            throw new RuntimeException("계좌의 잔액이 음수입니다.");
        }

        return userPoint.point();
    }


}
