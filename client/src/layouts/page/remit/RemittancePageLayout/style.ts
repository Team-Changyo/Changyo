import { styled } from 'styled-components';

export const RemittancePageLayoutContainer = styled.div`
	height: calc(100vh - 5rem);
	position: relative;
	margin-top: 5rem;

	.remit {
		width: 100%;
		position: absolute;
		bottom: 0;
		margin-bottom: 2rem;

		.guide-text {
			text-align: center;
			margin-bottom: 10px;
		}
	}

	.title-mb {
		margin-bottom: 1rem;
	}

	.group-mb {
		margin-bottom: 2rem;
	}
`;
