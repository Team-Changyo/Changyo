import { css, styled } from 'styled-components';

// 버튼 타입에 따른 스타일 매핑 객체
const buttonStyles = {
	Normal: css`
		background-color: var(--gray-100);
		color: var(--gray-400);
	`,
	Primary: css`
		background-color: var(--main-color);
		color: var(--white-color);
	`,
	Danger: css`
		background-color: var(--danger-color);
		color: var(--white-color);
	`,
};

export const ButtonWrapper = styled.div<{ $type: 'Normal' | 'Primary' | 'Danger' }>`
	position: relative;
	width: 100%;
	min-height: 3rem;
	max-height: 3rem;

	button {
		font-size: 1rem;
		font-weight: bold;
		border-radius: var(--radius-m);
		width: 100%;
		height: 3rem;
		${({ $type }) => buttonStyles[$type]}
	}
`;
