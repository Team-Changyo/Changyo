import { Input, Modal } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { ISettlement, ISettlementGroup } from 'types/deposit';
import UnderLineInput from 'components/atoms/common/UnderLineInput';
import Button from 'components/organisms/common/Button';
import { formatMoney } from 'utils/common/formatMoney';
import ReasonSelect from 'components/organisms/deposit/ReasonSelect';
import { DEPOSIT_VALUE_SELECT_OPTION_LIST } from 'constants/select';
import { DepositReturnModalContainer } from './style';

interface IDepositReturnModalProps {
	open: boolean;
	handleClose: () => void;
	toBeReturned: ISettlement[];
	settlementGroup: ISettlementGroup;
	returnDeposit: (returnMoney: number, reason: string, reasonDetail: string) => void;
}

function DepositReturnModal(props: IDepositReturnModalProps) {
	const { open, handleClose, toBeReturned, settlementGroup, returnDeposit } = props;
	const { moneyUnit } = settlementGroup;
	const [reason, setReason] = useState('선택');
	const [reasonDetail, setReasonDetail] = useState('');
	const [returnMoney, setReturnMoney] = useState(moneyUnit);
	const [showReasonOption, setShowReasonOption] = useState(false);
	const name = `${toBeReturned[0]?.depositorName}`;
	const others = toBeReturned.length >= 2 ? ` 외 ${toBeReturned.length - 1}명` : '';
	const formattedMoneyUnit = formatMoney(moneyUnit);

	const returnComplete = () => {
		returnDeposit(returnMoney, reason, reasonDetail);
		setReturnMoney(moneyUnit);
		setReason('선택');
		handleClose();
	};

	const returnCancel = () => {
		// TODO : 토스트로 교체하기
		alert('그러세요 네');
		setReturnMoney(moneyUnit);
		setReason('선택');
		handleClose();
	};

	useEffect(() => {
		if (returnMoney < moneyUnit) {
			setShowReasonOption(true);
		} else {
			setShowReasonOption(false);
		}
	}, [returnMoney]);

	return (
		<Modal open={open} onClose={returnCancel}>
			<DepositReturnModalContainer>
				<div className="content">
					<h2>
						<b>{settlementGroup.title}</b> 건<br />
						보증금을 반환합니다
					</h2>
					<h2>
						<b>{name}</b>님<b>{others}</b>에게
						<br />
						얼마를 반환할까요?
					</h2>
					<div className="money-unit">
						<UnderLineInput
							type="number"
							value={returnMoney}
							setValue={setReturnMoney}
							width="150px"
							unitText="원"
							placeholder=""
						/>
						<span>입금단위 {formattedMoneyUnit}원</span>
					</div>
					<div className="option">
						{showReasonOption ? (
							<>
								<h3 className="reason">
									<b>{moneyUnit - returnMoney}원이 차감된 이유</b>를
									<br />
									선택해주세요
								</h3>
								<ReasonSelect
									reason={reason}
									setReason={setReason}
									SELECT_OPTION_LIST={DEPOSIT_VALUE_SELECT_OPTION_LIST}
								/>
								{reason === '기타' ? (
									<Input
										type="text"
										placeholder="상세사유 입력"
										value={reasonDetail}
										onChange={(e) => setReasonDetail(e.target.value)}
									/>
								) : (
									<div />
								)}
							</>
						) : (
							<h3 className="success">전액을 반환합니다</h3>
						)}
					</div>
				</div>
				<div className="btn-group">
					<Button handleClick={returnComplete} text="반환 완료" type="Primary" />
					<Button handleClick={returnCancel} text="나중에 반환" type="Normal" />
				</div>
			</DepositReturnModalContainer>
		</Modal>
	);
}

export default DepositReturnModal;
