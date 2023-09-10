import { styled } from 'styled-components';

export const CheckTextWrapper = styled.span<{ $value: boolean }>`
	font-size: 1.2rem;
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;

	color: ${({ $value }) => ($value ? 'var(--main-color)' : 'var(--gray-400)')};

	svg {
		fill: ${({ $value }) => ($value ? 'var(--main-color)' : 'var(--gray-400)')};
	}

	&:hover {
		cursor: pointer;
	}
`;
