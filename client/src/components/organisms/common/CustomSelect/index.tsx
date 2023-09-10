import React, { Dispatch, SetStateAction } from 'react';
import { MenuItem, Select } from '@mui/material';
import { CustomStringSelectWrapper } from './style';

interface ICustomStringSelectProps {
	value: string;
	setValue: Dispatch<SetStateAction<string>>;
	SELECT_OPTIONS: { [key: string]: string }[];
	disabled: boolean;
}

function CustomStringSelect(props: ICustomStringSelectProps) {
	const { value, setValue, SELECT_OPTIONS, disabled } = props;

	return (
		<CustomStringSelectWrapper>
			<Select displayEmpty value={value} label="" onChange={(e) => setValue(e.target.value)} disabled={disabled}>
				<MenuItem key={0} value="" selected>
					선택
				</MenuItem>
				{SELECT_OPTIONS.map((el) => (
					<MenuItem key={el.key} value={el.key}>
						{el.value}
					</MenuItem>
				))}
			</Select>
		</CustomStringSelectWrapper>
	);
}

export default CustomStringSelect;
