import React from 'react';
import { ReactComponent as Check } from 'assets/icons/check.svg';
import { ReactComponent as Coin } from 'assets/icons/account/coin.svg';
import { ISettlement } from 'types/deposit';
import { SettlementListItemContainer } from './style';

interface ISettlementListItemProps {
	addToBeReturned: (settlement: ISettlement) => void;
	isMultiReturnMode: boolean;
	openReturnModal: () => void;
	settlement: ISettlement;
	toBeReturned: Array<ISettlement>;
}

function SettlementListItem(props: ISettlementListItemProps) {
	const { addToBeReturned, isMultiReturnMode, openReturnModal, settlement, toBeReturned } = props;
	const isReturn = settlement.status !== 'WAIT';

	const handleReturn = () => {
		toBeReturned.length = 0;
		addToBeReturned(settlement);
		openReturnModal();
	};

	const isSelected = () => {
		const idx = toBeReturned.findIndex((el) => el.tradeId === settlement.tradeId);
		if (idx === -1) return false;
		return true;
	};

	return (
		<SettlementListItemContainer $isReturned={isReturn}>
			<div className="settlement-logo">
				<Coin />
			</div>
			<div className="settlement-info">
				<div className="depositor-name">{settlement.memberName}</div>
				<div className="return-datetime">
					{settlement.tradeDate} <span>{isReturn ? '반환' : '입금'}</span>
				</div>
			</div>
			<div className="return-btn">
				{/* 반환된 건이면 완료 버튼 (비활성화 버튼) */}
				{isReturn ? (
					<button type="button" className="returned" disabled>
						완료
					</button>
				) : (
					// 반환되지 않은 건이면, 반환 버튼 or 체크 버튼
					<>
						{isMultiReturnMode ? (
							// 여러건 반환 모드일 때, 체크 버튼으로 전환
							<Check
								fill={isSelected() ? 'var(--main-color)' : 'var(--gray-400)'}
								onClick={() => addToBeReturned(settlement)}
							/>
						) : (
							// 단일건 반환 모드일 때, 반환 버튼으로 전환
							<button type="button" className="before-return" onClick={handleReturn}>
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
