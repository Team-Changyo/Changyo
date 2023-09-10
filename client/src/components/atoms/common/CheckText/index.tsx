import React, { Dispatch, SetStateAction } from 'react';
import { ReactComponent as Check } from 'assets/icons/check.svg';
import { CheckTextWrapper } from './style';

function CheckText({ value, setValue }: { value: boolean; setValue: Dispatch<SetStateAction<boolean>> }) {
	return (
		<CheckTextWrapper $value={value} onClick={() => setValue(!value)}>
			<Check />
			해당 계좌를 주 계좌로 사용할래요
		</CheckTextWrapper>
	);
}

export default CheckText;
