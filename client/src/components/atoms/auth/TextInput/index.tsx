import React, { Dispatch, SetStateAction } from 'react';
import { TextField } from '@mui/material';
import { TextInputWrapper } from './style';

interface ITextInputProps {
	value: string;
	setValue: Dispatch<SetStateAction<string>>;
	label: string;
	placeholder: string;
	type: string;
}
function TextInput({ value, setValue, label, type, placeholder }: ITextInputProps) {
	return (
		<TextInputWrapper>
			<TextField
				type={type}
				value={value}
				onChange={(e) => setValue(e.target.value)}
				label={label}
				placeholder={placeholder}
				variant="outlined"
			/>
		</TextInputWrapper>
	);
}

export default TextInput;
