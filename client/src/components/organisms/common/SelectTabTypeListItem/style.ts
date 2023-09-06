import { styled } from 'styled-components';

export const SelectTabTypeItemWrapper = styled.div<{ $selected: boolean }>`
	color: ${({ $selected }) => ($selected ? 'var(--gray-100)' : 'var(--gray-500)')};
	background-color: ${({ $selected }) => ($selected ? 'var(--main-color)' : 'var(--gray-100)')};
	border-radius: var(--radius-s);
	padding: 1rem 2rem;
	text-align: center;
`;
