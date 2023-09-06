import React from 'react';
import { SelectTabTypeItemWrapper } from './style';

interface ISelectTabTypeItemProps {
	title: string;
	handleClick: () => void;
	$selected: boolean;
}

function SelectTabTypeListItem({ title, handleClick, $selected }: ISelectTabTypeItemProps) {
	return (
		<SelectTabTypeItemWrapper onClick={handleClick} $selected={$selected}>
			{title}
		</SelectTabTypeItemWrapper>
	);
}

export default SelectTabTypeListItem;
