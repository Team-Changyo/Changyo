import { styled } from 'styled-components';

export const CertCodeInputContainer = styled.div`
	position: relative;
	width: 100%;
	display: flex;
	flex-direction: row;
	gap: 1rem;
	height: 100px;

	input {
		border: 1px solid var(--gray-400);
		border-radius: var(--radius-m);
		text-align: center;
		font-size: 3rem;
		width: 100%;

		&:focus-visible {
			outline: 2px solid var(--main-color);
		}
	}
`;
