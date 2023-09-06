import React from 'react';
import { ListTotalTextWrapper } from './style';

/**
 * 리스트 상단에 표기되는 작은 회색 텍스트 ex) 반환대기 n건, 관리 중 n건
 */
function ListTotalText({ text, totalCnt }: { text: string; totalCnt: number }) {
	return <ListTotalTextWrapper>{`${text} ${totalCnt}건`}</ListTotalTextWrapper>;
}

export default ListTotalText;
