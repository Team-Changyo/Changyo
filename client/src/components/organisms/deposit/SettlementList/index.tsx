import React, { Dispatch, SetStateAction, useEffect, useState } from 'react';
import { ReactComponent as Check } from 'assets/icons/check.svg';
import { ISettlement, ISettlementGroup } from 'types/deposit';
import ListTotalText from 'components/atoms/common/ListTotalText';
import Button from 'components/organisms/common/Button';
import { MultiReturnMenuWrapper, SettlementListContainer } from './style';
import SettlementListItem from '../SettlementListItem';
import DepositReturnModal from '../DepositReturnModal';

function MultiReturnMenu({
	selectedSettlement,
	setMultiReturnMode,
}: {
	selectedSettlement: Array<ISettlement>;
	setMultiReturnMode: Dispatch<SetStateAction<boolean>>;
}) {
	async function processSettlements(settlements: ISettlement[]) {
		for (const settlement of settlements) {
			await new Promise<void>((resolve) => {
				DepositReturnModal({
					depositorName: settlement.depositorName,
					onAction: () => {
						// 여기에 모달 닫는 로직을 추가할 수 있습니다.
						resolve();
					},
					params: {},
				});
			});
		}
	}

	const handleReturnClick = async () => {
		await processSettlements(selectedSettlement);
	};

	return (
		<MultiReturnMenuWrapper>
			<div className="multi-return-menu-container">
				<Button handleClick={handleReturnClick} text={`선택된 ${selectedSettlement.length}건 반환하기`} type="Danger" />
				<Button
					handleClick={() => {
						setMultiReturnMode(false);
					}}
					text="한건씩 반환하기"
					type="Normal"
				/>
			</div>
		</MultiReturnMenuWrapper>
	);
}

function SettlementList({
	settlements,
	isReturned,
	settlementGroup,
}: {
	settlements: ISettlement[];
	isReturned: boolean;
	settlementGroup: ISettlementGroup;
}) {
	const [multiReturnMode, setMultiReturnMode] = useState(false);
	const [multiReturnMenuOpen, setMultiReturnMenuOpen] = useState(false);
	const [selectedSettlement, setSelectedSettlement] = useState<ISettlement[]>([]);

	useEffect(() => {
		if (multiReturnMode) {
			setMultiReturnMenuOpen(true);
		} else {
			setMultiReturnMenuOpen(false);
		}
	}, [multiReturnMode]);
	return (
		<SettlementListContainer>
			<div className="top">
				<ListTotalText text={isReturned ? '반환 완료' : '반환 전'} totalCnt={settlements.length} />

				{/* 반환 전/ 반환 완료 탭에 따라 '여러건 선택' 버튼 출력 */}
				{isReturned ? (
					// 반환 완료 탭의 경우
					<div />
				) : (
					// 반환 전 탭의 경우
					<div className="multi-return-btn">
						{multiReturnMode ? (
							<span
								onClick={() => {
									selectedSettlement.length = 0;
									settlements.map((el) => selectedSettlement.push(el));
									setSelectedSettlement([...selectedSettlement]);
								}}
								role="presentation"
							>
								전체선택
							</span>
						) : (
							<div
								onClick={() => {
									setMultiReturnMode(true);
									setMultiReturnMenuOpen(true);
								}}
								role="presentation"
							>
								<Check />
								<span>여러건 반환</span>
							</div>
						)}
					</div>
				)}
			</div>

			{/* 정산 사항 리스트 */}
			{settlements.map((el) => (
				<SettlementListItem
					key={el.key}
					settlement={el}
					multiReturnMode={multiReturnMode}
					selectedSettlement={selectedSettlement}
					setSelectedSettlement={setSelectedSettlement}
					settlementGroup={settlementGroup}
				/>
			))}

			{/* 여러건 반환 하단 메뉴 */}
			{multiReturnMenuOpen ? (
				<MultiReturnMenu setMultiReturnMode={setMultiReturnMode} selectedSettlement={selectedSettlement} />
			) : (
				<div />
			)}
		</SettlementListContainer>
	);
}

export default SettlementList;
