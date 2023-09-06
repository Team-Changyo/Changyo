import React from 'react';
import { SelectTabTypeContainer } from './style';
import SelectTabTypeListItem from '../SelectTabTypeListItem';

interface ISelectTabTypeProps<T> {
	tabTypes: { idx: number; title: string; handleClick: () => void; selected: T }[];
}

function SelectTabTypeList<T>({ tabTypes }: ISelectTabTypeProps<T>) {
	return (
		<SelectTabTypeContainer>
			{tabTypes.map((el) => (
				<SelectTabTypeListItem
					key={el.idx}
					title={el.title}
					handleClick={el.handleClick}
					$selected={el.idx === el.selected}
				/>
			))}
		</SelectTabTypeContainer>
	);
}

export default SelectTabTypeList;
