import React, { useEffect, useState } from 'react';
import { ReactComponent as Check } from 'assets/icons/check.svg';
import { ISettlement } from 'types/deposit';
import ListTotalText from 'components/atoms/common/ListTotalText';
import { SettlementListContainer } from './style';
import SettlementListItem from '../SettlementListItem';
import SettlementMultiReturnMenu from '../SettlementMultiReturnMenu';
import DepositReturnModal from '../DepositReturnModal';

interface ISettlementListProps {
	settlements: ISettlement[];
	isReturned: boolean;
	title: string;
	moneyUnit: number;
}

function SettlementList({ settlements, isReturned, title, moneyUnit }: ISettlementListProps) {
	const [isMultiReturnMode, setIsMultiReturnMode] = useState(false);
	const [activeMultiReturnMenu, setActiveMultiReturnMenu] = useState(false);
	const [toBeReturned, setToBeReturned] = useState<ISettlement[]>([]);
	const [modalOpen, setModalOpen] = useState(false);

	const addToBeReturned = (settlement: ISettlement) => {
		const idx = toBeReturned.findIndex((el) => el.tradeId === settlement.tradeId);
		if (idx === -1) {
			toBeReturned.push(settlement);
			setToBeReturned([...toBeReturned]);
		} else {
			toBeReturned.splice(idx, 1);
			setToBeReturned([...toBeReturned]);
		}
	};

	// 최종 반환 함수
	const returnDeposit = (returnMoney: number, reason: string, reasonDetail: string) => {
		// TODO : 반환 API 나오면 처리할 것.

		console.log(toBeReturned);
		console.log(returnMoney);
		console.log(reason);
		console.log(reasonDetail);

		setIsMultiReturnMode(false);
	};

	// 전체 선택 버튼 클릭 시
	const selectAll = () => {
		toBeReturned.length = 0;
		settlements.map((el) => toBeReturned.push(el));
		setToBeReturned([...toBeReturned]);
	};

	// 여러건 선택 버튼 클릭 시
	const multiReturn = () => {
		toBeReturned.length = 0;
		setIsMultiReturnMode(true);
		setActiveMultiReturnMenu(true);
	};

	// 반환 모달 열기/닫기
	const openReturnModal = () => {
		setModalOpen(true);
	};

	const closeReturnModal = () => {
		setModalOpen(false);
	};

	useEffect(() => {
		if (isMultiReturnMode) {
			setActiveMultiReturnMenu(true);
		} else {
			setActiveMultiReturnMenu(false);
		}
	}, [isMultiReturnMode]);

	return (
		<SettlementListContainer>
			<div className="top">
				<ListTotalText text={!isReturned ? '반환 전' : '반환 완료'} totalCnt={settlements?.length} />
				{/* 반환 전/반환 완료 탭에 따라 버튼 메뉴 출력 */}
				{!isReturned ? (
					// '반환 전' 탭
					<div className="multi-return-btn">
						{/* 단일건 반환 모드일 경우, 여러건 반환 버튼  */}
						{!isMultiReturnMode ? (
							<div onClick={multiReturn} role="presentation">
								<Check />
								<span>여러건 반환</span>
							</div>
						) : (
							// 여러건 반환 모드일 경우, 전체선택 버튼
							<span onClick={selectAll} role="presentation">
								전체선택
							</span>
						)}
					</div>
				) : (
					// '반환 완료' 탭
					<div />
				)}
			</div>

			{/* 정산 사항 리스트 */}
			{settlements?.length ? (
				settlements.map((el) => (
					<SettlementListItem
						key={el.tradeId}
						addToBeReturned={addToBeReturned}
						isMultiReturnMode={isMultiReturnMode}
						openReturnModal={openReturnModal}
						settlement={el}
						toBeReturned={toBeReturned}
					/>
				))
			) : (
				<div>정산사항이 없습니다.</div>
			)}

			{/* 여러건 반환 하단 메뉴 */}
			{activeMultiReturnMenu ? (
				<SettlementMultiReturnMenu
					setIsMultiReturnMode={setIsMultiReturnMode}
					toBeReturned={toBeReturned}
					openReturnModal={openReturnModal}
				/>
			) : (
				<div />
			)}

			{/* 반환 모달 */}
			<DepositReturnModal
				open={modalOpen}
				handleClose={closeReturnModal}
				toBeReturned={toBeReturned}
				returnDeposit={returnDeposit}
				title={title}
				moneyUnit={moneyUnit}
			/>
		</SettlementListContainer>
	);
}

export default SettlementList;
