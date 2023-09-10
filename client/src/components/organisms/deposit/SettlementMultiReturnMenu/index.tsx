import React, { Dispatch, SetStateAction } from 'react';
import { ISettlement } from 'types/deposit';

import Button from 'components/organisms/common/Button';
import { SettleMultiReturnMenuWrapper } from './style';

interface IMultiReturnMenuProps {
	toBeReturned: Array<ISettlement>;
	setIsMultiReturnMode: Dispatch<SetStateAction<boolean>>;
	openReturnModal: () => void;
}

function SettlementMultiReturnMenu({ toBeReturned, setIsMultiReturnMode, openReturnModal }: IMultiReturnMenuProps) {
	const handleClick = () => {
		// TODO : toast로 교체하기
		if (toBeReturned.length === 0) {
			alert('선택된 건이 없어연');
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
