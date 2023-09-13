import React, { Dispatch, SetStateAction } from 'react';
import { ISettlement } from 'types/deposit';

import Button from 'components/organisms/common/Button';
import { toast } from 'react-hot-toast';
import { SettleMultiReturnMenuWrapper } from './style';

interface IMultiReturnMenuProps {
	toBeReturned: Array<ISettlement>;
	setIsMultiReturnMode: Dispatch<SetStateAction<boolean>>;
	openReturnModal: () => void;
}

function SettlementMultiReturnMenu({ toBeReturned, setIsMultiReturnMode, openReturnModal }: IMultiReturnMenuProps) {
	const handleClick = () => {
		if (toBeReturned.length === 0) {
			toast.error('선택된 반환 항목이 없습니다');
		} else {
			openReturnModal();
		}
	};
	return (
		<SettleMultiReturnMenuWrapper>
			<div className="multi-return-menu-container">
				<Button
					handleClick={handleClick}
					text={`선택된 ${toBeReturned.length}건 반환하기`}
					type={toBeReturned.length ? 'Danger' : 'Normal'}
				/>
				<Button
					handleClick={() => {
						setIsMultiReturnMode(false);
					}}
					text="한건씩 반환하기"
					type="Normal"
				/>
			</div>
		</SettleMultiReturnMenuWrapper>
	);
}

export default SettlementMultiReturnMenu;
