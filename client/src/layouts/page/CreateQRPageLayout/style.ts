import { styled } from 'styled-components';

export const CreateQRPageLayoutContainer = styled.div`
	height: calc(100vh - 5rem); // 100vh - navigation height
	position: relative;
	margin-top: 5rem;

	.title-mb {
		margin-bottom: 1rem;
	}

	.group-mb {
		margin-bottom: 2rem;
	}
	.create-qr-btn {
		position: absolute;
		width: 100%;
		bottom: 2rem;
	}
`;
