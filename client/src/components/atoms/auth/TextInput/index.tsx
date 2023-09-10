import React, { Dispatch, SetStateAction } from 'react';
import { TextField } from '@mui/material';
import { TextInputWrapper } from './style';

interface ITextInputProps {
	value: string;
	setValue: Dispatch<SetStateAction<string>>;
	placeholder: string;
}
function TextInput({ value, setValue, placeholder }: ITextInputProps) {
	return (
		<TextInputWrapper>
			<TextField value={value} onChange={(e) => setValue(e.target.value)} label={placeholder} variant="outlined" />
		</TextInputWrapper>
	);
}

export default TextInput;
