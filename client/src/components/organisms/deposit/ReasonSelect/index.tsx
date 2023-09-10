import { MenuItem, Select } from '@mui/material';
import React, { Dispatch, SetStateAction } from 'react';
import { ReasonSelectWrapper } from './style';

interface IReasonSelectProps {
	reason: string;
	setReason: Dispatch<SetStateAction<string>>;
	SELECT_OPTION_LIST: { key: number; value: string }[];
}
function ReasonSelect(props: IReasonSelectProps) {
	const { reason, setReason, SELECT_OPTION_LIST } = props;
	return (
		<ReasonSelectWrapper>
			<Select value={reason} label="" onChange={(e) => setReason(e.target.value)}>
				{SELECT_OPTION_LIST.map((el) => (
					<MenuItem key={el.key} value={el.value}>
						{`${el.value}`}
					</MenuItem>
				))}
			</Select>
		</ReasonSelectWrapper>
	);
}

export default ReasonSelect;
