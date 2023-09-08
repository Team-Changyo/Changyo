import { styled } from 'styled-components';

export const PageLayoutContainer = styled.div<{ $paddingBottom: string }>`
	position: relative;
	max-width: 500px;
	min-height: 100vh;
	max-height: 100vh;
	overflow-y: scroll;
	padding: 0 15px;
	padding-bottom: ${({ $paddingBottom }) => $paddingBottom};
`;
