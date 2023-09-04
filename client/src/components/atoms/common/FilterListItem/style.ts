import { styled } from 'styled-components';

export const FilterListItemWrapper = styled.div<{ $isActive: boolean }>`
	span {
		color: ${({ $isActive }) => ($isActive ? 'var(--black-500)' : 'var(--gray-300)')};
		font-size: 1.3em;
	}

	&:hover {
		cursor: pointer;
	}
`;
