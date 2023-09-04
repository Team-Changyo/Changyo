import React, { Dispatch, SetStateAction } from 'react';
import { FilterListItemWrapper } from './style';

interface IFilterlistItemProps<T> {
	value: T;
	$isActive: boolean;
	text: string;
	setSelected: Dispatch<SetStateAction<T>>;
}
function FilterListItem<T>({ value, $isActive, text, setSelected }: IFilterlistItemProps<T>) {
	const handleClick = () => {
		setSelected(value);
	};

	return (
		<FilterListItemWrapper $isActive={$isActive} onClick={handleClick}>
			<span>{text}</span>
		</FilterListItemWrapper>
	);
}

export default FilterListItem;
