import React, { Dispatch, SetStateAction } from 'react';
import { ReactComponent as Check } from 'assets/icons/check.svg';
import { CheckTextWrapper } from './style';

function CheckText({
	value,
	setValue,
	text,
}: {
	value: boolean;
	setValue: Dispatch<SetStateAction<boolean>>;
	text: string;
}) {
	return (
		<CheckTextWrapper $value={value} onClick={() => setValue(!value)}>
			<Check />
			{text}
		</CheckTextWrapper>
	);
}

export default CheckText;
