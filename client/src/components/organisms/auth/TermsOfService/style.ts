import { styled } from 'styled-components';

export const TermsOfServiceContainer = styled.div`
	position: relative;
	margin-top: 4rem;
	height: calc(100vh - 4rem);

	svg:hover {
		cursor: pointer;
	}

	.agree-all {
		border: 1px solid var(--gray-300);
		border-radius: var(--radius-s);
		padding: 10px 20px;

		span {
			display: flex;
			align-items: center;
			gap: 0.4rem;

			&:hover {
				cursor: pointer;
			}

			svg {
				width: 24px;
				height: 24px;
			}
		}
	}

	.agree-list {
		display: flex;
		flex-direction: column;
		gap: 20px;
		padding: 10px 23px;

		div {
			display: flex;
			gap: 0.5rem;

			&:hover {
				cursor: pointer;
			}

			svg {
				width: 20px;
				height: 20px;
			}

			span {
				line-height: 20px;
				font-size: 0.9rem;
			}
		}
	}
	.next-btn {
		position: absolute;
		width: 100%;
		bottom: 2rem;
	}
`;
