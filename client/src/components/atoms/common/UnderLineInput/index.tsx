import React from 'react';
import Input from '@mui/material/Input';
import { UnderLineInputWrapper } from './style';

interface IUnderLineInputProps {
	placeholder: string;
	width: string;
	unitText?: string;
}
function UnderLineInput({ placeholder, unitText, width }: IUnderLineInputProps) {
	return (
		<UnderLineInputWrapper $width={width}>
			<Input placeholder={placeholder} /> {unitText}
		</UnderLineInputWrapper>
	);
}

export default UnderLineInput;
