import { styled } from 'styled-components';

export const UnderLineInputWrapper = styled.div<{ $width: string }>`
	width: fit-content;

	div {
		&::before {
			border-bottom: 2px solid var(--gray-300);
		}
	}
	input {
		padding-left: 5px;
		width: ${({ $width }) => $width};
		font-size: 1.2rem;
		font-family: 'Pretendard';
	}
`;
