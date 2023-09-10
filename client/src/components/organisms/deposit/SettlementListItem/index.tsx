import React, { Dispatch, SetStateAction } from 'react';
import { ReactComponent as Check } from 'assets/icons/check.svg';
import { ReactComponent as Coin } from 'assets/icons/account/coin.svg';
import { ISettlement, ISettlementGroup } from 'types/deposit';
import { SettlementListItemContainer } from './style';
import DepositReturnModal from '../DepositReturnModal';

interface ISettlementListItemProps {
	settlement: ISettlement;
	multiReturnMode: boolean;
	selectedSettlement: Array<ISettlement>;
	setSelectedSettlement: Dispatch<SetStateAction<ISettlement[]>>;
	settlementGroup: ISettlementGroup;
}

function SettlementListItem({
	settlement,
	multiReturnMode,
	selectedSettlement,
	setSelectedSettlement,
	settlementGroup,
}: ISettlementListItemProps) {
	const isSelected = () => {
		const idx = selectedSettlement.findIndex((el) => el.key === settlement.key);
		if (idx === -1) return false;
		return true;
	};

	const handleReturnBtnClick = () => {
		DepositReturnModal({ depositorName: settlement.depositorName, onAction: () => {}, params: { settlementGroup } });
	};

	const handleCheckBtnClick = () => {
		const idx = selectedSettlement.findIndex((el) => el.key === settlement.key);
		if (idx === -1) {
			selectedSettlement.push(settlement);
			setSelectedSettlement([...selectedSettlement]);
		} else {
			selectedSettlement.splice(idx, 1);
			setSelectedSettlement([...selectedSettlement]);
		}
	};
	return (
		<SettlementListItemContainer $isReturned={settlement.isReturned}>
			<div className="settlement-logo">
				<Coin />
			</div>
			<div className="settlement-info">
				<div className="depositor-name">
					입금자명 <span>{settlement.depositorName}</span>
				</div>
				<div className="return-datetime">
					입금일시 <span>{settlement.dateTime}</span>
				</div>
			</div>
			<div className="return-btn">
				{/* 반환된 건이면 완료 버튼 (비활성화 버튼) */}
				{settlement.isReturned ? (
					<button type="button" className="returned" disabled>
						완료
					</button>
				) : (
					// 반환되지 않은 건이면, 반환 버튼 or 체크 버튼
					<>
						{multiReturnMode ? (
							// 여러건 반환 모드일 때, 체크 버튼으로 전환
							<Check fill={isSelected() ? 'var(--main-color)' : 'var(--gray-400)'} onClick={handleCheckBtnClick} />
						) : (
							// 단일건 반환 모드일 때, 반환 버튼으로 전환
							<button type="button" className="before-return" onClick={handleReturnBtnClick}>
								반환
							</button>
						)}
					</>
				)}
			</div>
		</SettlementListItemContainer>
	);
}

export default SettlementListItem;
